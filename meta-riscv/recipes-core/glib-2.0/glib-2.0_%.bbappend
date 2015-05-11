FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://riscv.cache"

EXTRA_OECONF += " --cache-file=../riscv.cache"

