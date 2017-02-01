PV = "6.1.0"
BINV = "6.1.0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://gcc-5.3-riscv.patch file://gcc-5.3-riscv-musl.patch"
