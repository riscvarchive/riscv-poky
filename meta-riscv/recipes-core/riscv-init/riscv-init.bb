SUMMARY = "Poky-tiny init for riscv"
DESCRIPTION = "Basic init system for poky-tiny for use with riscv"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

RDEPENDS_${PN} = "busybox"

SRC_URI = "file://inittab \
	  "

do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	install -d ${D}${sysconfdir}
	install -m 0755 ${WORKDIR}/inittab ${D}${sysconfdir}
}

FILES_${PN} = "${sysconfdir}/inittab"
