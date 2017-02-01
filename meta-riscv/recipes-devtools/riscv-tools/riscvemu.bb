SUMMARY = "RISCVEMU ISA Simulator"
DESCRIPTION = "RISCVEMU ISA Simulator"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://bellard.org/riscvemu/riscvemu-2017-01-12.tar.gz"
SRC_URI[md5sum] = "94e1173749d42e65370c6a44bc51ddf8"
SRC_URI[sha256sum] = "fdc5505a7f5e1d8cd33687eaf8a164eb978a120d15ac9ff611fe238382c61175"

S = "${WORKDIR}/riscvemu-2017-01-12"

do_configure_prepend () {
  sed -i 's/-Werror/ /' ${B}/Makefile
}

# Nothing to do, just leave riscvemu in the build directory
do_install () {
}

inherit cross-canadian

BBCLASSEXTEND = "native nativesdk"
