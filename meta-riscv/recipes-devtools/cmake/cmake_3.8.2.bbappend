FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append += "file://KWIML-riscv.patch"
