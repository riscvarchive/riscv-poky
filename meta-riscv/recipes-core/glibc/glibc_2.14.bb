require recipes-core/glibc/glibc.inc

DEPENDS += "gperf-native kconfig-frontends-native"

PV = "2.14.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "${GNU_MIRROR}/glibc/glibc-2.14.1.tar.bz2 \
           file://glibc-2.14.1-riscv.patch \
           file://glibc-2.14.1-makefile.patch \
           file://etc/ld.so.conf \
           file://generate-supported.mk \
          "

SRC_URI[md5sum] = "5869a2620c6917dd392289864c6ce595"
SRC_URI[sha256sum] = "6e85a2fa3ebe6b28103361f09d27eeda37a021f24dab73f34064456d5a715b3b"

LIC_FILES_CHKSUM = "file://LICENSES;md5=98a1128c4b58120182cbea3b1752d8b9 \
      file://COPYING;md5=393a5ca445f6965873eca0259a17f833 \
      file://posix/rxspencer/COPYRIGHT;md5=dc5485bb394a13b2332ec1c785f5d83a \
      file://COPYING.LIB;md5=bbb461211a33b134d42ed5ee802b37ff"

#S = "${WORKDIR}/git"
B = "${WORKDIR}/build-${TARGET_SYS}"

PACKAGES_DYNAMIC = ""

# the -isystem in bitbake.conf screws up glibc do_stage
BUILD_CPPFLAGS = "-I${STAGING_INCDIR_NATIVE}"
TARGET_CPPFLAGS = "-I${STAGING_DIR_TARGET}${includedir}"

GLIBC_BROKEN_LOCALES = " _ER _ET so_ET yn_ER sid_ET tr_TR mn_MN gez_ET gez_ER bn_BD te_IN es_CR.ISO-8859-1"

#
# For now, we will skip building of a gcc package if it is a uclibc one
# and our build is not a uclibc one, and we skip a glibc one if our build
# is a uclibc build.
#
# See the note in gcc/gcc_3.4.0.oe
#

python __anonymous () {
    import re
    uc_os = (re.match('.*uclibc$', d.getVar('TARGET_OS', True)) != None)
    if uc_os:
        raise bb.parse.SkipPackage("incompatible with target %s" %
                                   d.getVar('TARGET_OS', True))
}

EXTRA_OECONF = "--host=riscv-linux --target=riscv-linux \
                libc_cv_forced_unwind=yes \
                libc_cv_c_cleanup=yes \
                --enable-shared \
                --enable-__thread \
                --disable-multilib \
                --enable-add-ons=nptl \
                --with-headers=${STAGING_INCDIR} \
		--with-kconfig=${STAGING_BINDIR_NATIVE} \
		${GLIBC_EXTRA_OECONF}"

#EXTRA_OECONF += "${@get_libc_fpu_setting(bb, d)}"

#do_configure () {
#	sed -ie 's,{ (exit 1); exit 1; }; },{ (exit 0); }; },g' ${S}/configure
#	chmod +x ${S}/configure
#	(cd ${S} && gnu-configize) || die "failure in running gnu-configize"
#	find ${S} -name "configure" | xargs touch

#	${S}/configure \
#                --build=${BUILD_SYS} \
#                --host=${TARGET_SYS} \
#                --prefix=/usr \
#                libc_cv_forced_unwind=yes \
#                libc_cv_c_cleanup=yes \
#                --enable-shared \
#                --enable-__thread \
#                --disable-multilib \
#                --enable-add-ons=nptl \
#		--with-headers=${STAGING_DIR_TARGET}${includedir}
#}

do_configure () {
# override this function to avoid the autoconf/automake/aclocal/autoheader
# calls for now
# don't pass CPPFLAGS into configure, since it upsets the kernel-headers
# version check and doesn't really help with anything
        if [ -z "`which rpcgen`" ]; then
                echo "rpcgen not found.  Install glibc-devel."
                exit 1
        fi
        #(cd ${S} && gnu-configize) || die "failure in running gnu-configize"
        find ${S} -name "configure" | xargs touch
        CPPFLAGS="" oe_runconf
}

#rpcsvc = "bootparam_prot.x nlm_prot.x rstat.x \
#	  yppasswd.x klm_prot.x rex.x sm_inter.x mount.x \
#	  rusers.x spray.x nfs_prot.x rquota.x key_prot.x"

do_compile () {
	# -Wl,-rpath-link <staging>/lib in LDFLAGS can cause breakage if another glibc is in staging
	unset LDFLAGS
	base_do_compile
	(
		cd ${S}/sunrpc/rpcsvc
		for r in ${rpcsvc}; do
			h=`echo $r|sed -e's,\.x$,.h,'`
			rpcgen -h $r -o $h || bbwarn "unable to generate header for $r"
		done
	)
	echo "Adjust ldd script"
	if [ -n "${RTLDLIST}" ]
	then
		prevrtld=`cat ${B}/elf/ldd | grep "^RTLDLIST=" | sed 's#^RTLDLIST="\?\([^"]*\)"\?$#\1#'`
		if [ "${prevrtld}" != "${RTLDLIST}" ]
		then
			sed -i ${B}/elf/ldd -e "s#^RTLDLIST=.*\$#RTLDLIST=\"${prevrtld} ${RTLDLIST}\"#"
		fi
	fi

}

require recipes-core/glibc/glibc-package.inc

FILES_${PN} += "${datadir}"

BBCLASSEXTEND = "nativesdk"
