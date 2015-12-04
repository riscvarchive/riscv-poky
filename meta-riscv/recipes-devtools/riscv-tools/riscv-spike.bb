SUMMARY = "RISC-V Spike ISA Simulator"
DESCRIPTION = "RISC-V Spike ISA Simulator"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "e78da5da4a6e6a38953cda9b35992c421768c776"
SRC_URI = "git://github.com/riscv/riscv-isa-sim.git \
           file://spike-makefile.patch"

DEPENDS = "riscv-fesvr"
RDEPENDS_nativesdk-riscv-spike = "nativesdk-riscv-fesvr"

inherit autotools cross-canadian

BBCLASSEXTEND = "native nativesdk"

S = "${WORKDIR}/git"

do_configure_prepend () {
        if [ ! -e ${S}/acinclude.m4 ]; then
                cp ${S}/aclocal.m4 ${S}/acinclude.m4
        fi
}
