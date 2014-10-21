require glibc_${PV}.bb
require recipes-core/glibc/glibc-initial.inc

DEPENDS += "kconfig-frontends-native"

# main glibc recipes muck with TARGET_CPPFLAGS to point into
# final target sysroot but we
# are not there when building glibc-initial
# so reset it here

TARGET_CPPFLAGS = ""

do_configure () {
	sed -ie 's,{ (exit 1); exit 1; }; },{ (exit 0); }; },g' ${S}/configure
	chmod +x ${S}/configure
	(cd ${S} && gnu-configize) || die "failure in running gnu-configize"
	find ${S} -name "configure" | xargs touch
	#${S}/configure --host=${TARGET_SYS} --build=${BUILD_SYS} \
	#	--target=riscv-linux
	#	--prefix=/usr \
	#	--without-cvs --disable-sanity-checks \
	#	--with-headers=${STAGING_DIR_TARGET}${includedir} \
	#	${EXTRA_OECONF}

	${S}/configure \
                --build=${BUILD_SYS} \
                --host=${TARGET_SYS} \
                --prefix=/usr \
                libc_cv_forced_unwind=yes \
                libc_cv_c_cleanup=yes \
                --enable-shared \
                --enable-__thread \
                --disable-multilib \
                --enable-add-ons=nptl \
		--with-headers=${STAGING_DIR_TARGET}${includedir}
}
