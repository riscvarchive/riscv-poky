require recipes-devtools/gcc/gcc-${PV}.inc
require recipes-devtools/gcc/libgcc-initial.inc

EXTRA_OECONF += "--disable-shared \
                 --disable-threads \
                 --enable-languages=c \
                 --with-newlib \
                 --disable-libmudflap \
                 --disable-libssp \
                 --disable-libquadmath \
                 --disable-libgomp \
                 --disable-nls \
                 --disable-multilib \
                 --disable-bootstrap"

do_install_append () {
  ln -s libgcc.a ${D}${libdir}/${TARGET_SYS}/${BINV}/libgcc_eh.a
}

EXTRA_OEMAKE = "inhibit-libc=true"
