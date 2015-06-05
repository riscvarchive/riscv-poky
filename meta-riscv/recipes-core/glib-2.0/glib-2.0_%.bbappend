FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://glib-2.0_riscv.cache"
EXTRA_OECONF += " --cache-file=../glib-2.0_riscv.cache"

