FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

CONFIGFILESURI_riscv = " \
    file://config.h \
    file://_numpyconfig.h \
"

SRC_URI += "file://numpy-riscv.patch"
