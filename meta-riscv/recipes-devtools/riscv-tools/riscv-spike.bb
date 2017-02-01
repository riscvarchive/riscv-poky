SUMMARY = "RISC-V Spike ISA Simulator"
DESCRIPTION = "RISC-V Spike ISA Simulator"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "e9e30598e08e4f162b523f9ef07f1510f3cfe0a6"
SRC_URI = "git://github.com/riscv/riscv-isa-sim.git"

DEPENDS = "riscv-fesvr"
RDEPENDS_${PN}_class-nativesdk = "nativesdk-riscv-fesvr"

EXTRA_OECONF_class-nativesdk = "--with-fesvr=${STAGING_DIR_HOST}${libdir}/../.."

inherit autotools cross-canadian

BBCLASSEXTEND = "native nativesdk"

S = "${WORKDIR}/git"

do_configure_prepend () {
	touch ${S}/softfloat/softfloat.ac
        if [ ! -e ${S}/acinclude.m4 ]; then
                cp ${S}/aclocal.m4 ${S}/acinclude.m4
        fi
}

# TODO: As soon as Spike is used again, fix the RPATHS
INSANE_SKIP_${PN} = "rpaths"

do_install_prepend () {
        chrpath -d ${B}/spike
        chrpath -d ${B}/spike-dasm
        chrpath -d ${B}/xspike
        chrpath -d ${B}/termios-xspike
        chrpath -d ${B}/libdummy_rocc.so
        chrpath -d ${B}/libriscv.so
        chrpath -d ${B}/libspike_main.so
        chrpath -d ${B}/libsoftfloat.so
}
