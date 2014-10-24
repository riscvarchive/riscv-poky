FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://glibc-2.20-riscv.patch"

EXTRA_OECONF_remove = "--enable-add-ons"
