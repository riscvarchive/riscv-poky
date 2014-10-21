SUMMARY = "RISC-V Tools"
DESCRIPTION = "RISC-V Patches for development tools"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://binutils-2.21.1-riscv.patch \
           file://gcc-4.6.1-riscv.patch \
           file://glibc-2.14.1-riscv.patch"

do_patch() {
	:
}

