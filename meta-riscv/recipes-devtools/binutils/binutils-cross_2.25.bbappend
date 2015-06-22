FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://binutils-2.25-riscv.patch file://binutils-2.25-elf-adjust-dynamic-copy.patch"

EXTRA_OECONF += "--enable-tls --disable-multilib"
