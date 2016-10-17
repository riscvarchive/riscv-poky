FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://binutils-2.27-riscv.patch"

EXTRA_OECONF += "--enable-tls --disable-multilib"
