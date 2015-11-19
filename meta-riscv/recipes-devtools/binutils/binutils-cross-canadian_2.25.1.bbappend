FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://binutils-2.25.1-riscv.patch"

EXTRA_OECONF += "--enable-tls --disable-multilib"
