SUMMARY = "RISCVEMU ISA Simulator"
DESCRIPTION = "RISCVEMU ISA Simulator"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://bellard.org/riscvemu/riscvemu-2017-08-06.tar.gz"
SRC_URI[md5sum] = "5d8ffc2d9966b900794c9cbb477ff16a"
SRC_URI[sha256sum] = "aeee64ee7c10ff9260152de999c131bf030daa8a121285d879e6d0ee4a629f21"

DEPENDS_class-native = "openssl-native openssl"

S = "${WORKDIR}/riscvemu-2017-08-06"

inherit cross-canadian

BBCLASSEXTEND = "native nativesdk"

do_configure_prepend () {
  sed -i 's,-Werror,-Wno-unused-value -Wno-unused-result -I${includedir} -L${libdir},' ${B}/Makefile
  sed -i 's,CONFIG_SDL=y,#CONFIG_SDL=y,' ${B}/Makefile
  sed -i 's,^LDFLAGS=,^LDFLAGS=-L${libdir},' ${B}/Makefile
}

# Nothing to do, just leave riscvemu in the build directory
do_install () {
}
