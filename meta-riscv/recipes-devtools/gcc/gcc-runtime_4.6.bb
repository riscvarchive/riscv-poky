require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/gcc-runtime.inc

EXTRA_OECONF += "--enable-shared \
                 --disable-threads \
                 --enable-tls \
                 --enable-languages=c,c++ \
                 --disable-libmudflap \
                 --disable-libssp \
                 --disable-libquadmath \
                 --disable-nls \
                 --disable-multilib"

RUNTIMETARGET = "libstdc++-v3"

do_configure () {
        export CXX="${CXX} -nostdinc++"
        mtarget=`echo ${TARGET_SYS} | sed -e s#-${SDKPKGSUFFIX}##`
        target=`echo ${TARGET_SYS} | sed -e s#-${SDKPKGSUFFIX}##`
        hardlinkdir ${STAGING_INCDIR_NATIVE}/gcc-build-internal-$mtarget ${B}
        for d in libgcc ${RUNTIMETARGET}; do
                echo "Configuring $d"
                rm -rf ${B}/$target/$d/
                mkdir -p ${B}/$target/$d/
                cd ${B}/$target/$d/
                chmod a+x ${S}/$d/configure
                ${S}/$d/configure ${CONFIGUREOPTS} ${EXTRA_OECONF}
        done
}

