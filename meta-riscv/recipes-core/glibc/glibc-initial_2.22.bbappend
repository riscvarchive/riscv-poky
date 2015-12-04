FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://glibc-2.22-riscv.patch"

EXTRA_OECONF_remove = "--enable-add-ons"
