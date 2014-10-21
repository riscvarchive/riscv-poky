require recipes-devtools/binutils/binutils.inc
require binutils-${PV}.inc
require recipes-devtools/binutils/binutils-cross.inc

#FILESEXTRAPATHS_prepend := "${WORKDIR}/riscv-tools/1.0-r0"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://binutils-2.21.1-riscv.patch"
