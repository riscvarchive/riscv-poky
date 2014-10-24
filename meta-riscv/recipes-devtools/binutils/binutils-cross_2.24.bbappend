FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://binutils-2.24-riscv.patch"

EXTRA_OECONF += "--enable-tls --disable-multilib"
