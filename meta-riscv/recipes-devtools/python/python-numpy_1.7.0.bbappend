FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

CONFIGFILESURI_riscv = " \
    file://config.h \
    file://_numpyconfig.h \
"

SRC_URI_append_riscv += "file://riscv/numpy-riscv.patch"
