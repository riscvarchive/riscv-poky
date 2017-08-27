FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

CONFIGFILESURI_riscv64 = " \
    file://config.h \
    file://_numpyconfig.h \
"

SRC_URI_append_riscv64 += "file://numpy-riscv.patch"
