SUMMARY = "Network support for riscv-linux in QEMU"
DESCRIPTION = "Scripts required for using networking within QEMU"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

RDEPENDS_${PN} = "busybox riscv-init"

SRC_URI = "file://default.script \
	  "

do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	install -m 0755 -d ${D}${datadir}/udhcpc
	install -m 0755 ${WORKDIR}/default.script ${D}${datadir}/udhcpc
}

FILES_${PN} = "${datadir}/udhcpc/default.script"
