SUMMARY = "RISCVEMU ISA Simulator"
DESCRIPTION = "RISCVEMU ISA Simulator"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://bellard.org/riscvemu/riscvemu-2017-06-10.tar.gz"
SRC_URI[md5sum] = "89d6ad40fc5db54cc60d90ddf4172cf5"
SRC_URI[sha256sum] = "25b6506b3f7512daf095da56d98d815333e6624df982d4d3c17a167b4d3a3124"

DEPENDS_class-native = "openssl-native"

S = "${WORKDIR}/riscvemu-2017-06-10"

inherit cross-canadian

BBCLASSEXTEND = "native nativesdk"

do_configure_prepend () {
  sed -i 's,-Werror,-Wno-unused-value -I${includedir} -L${libdir},' ${B}/Makefile
  sed -i 's,^LDFLAGS=,^LDFLAGS=-L${libdir},' ${B}/Makefile
}

# Nothing to do, just leave riscvemu in the build directory
do_install () {
}
