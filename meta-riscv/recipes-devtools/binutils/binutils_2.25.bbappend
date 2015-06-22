FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append_riscv = "file://binutils-2.25-riscv.patch file://binutils-2.25-elf-adjust-dynamic-copy.patch"
