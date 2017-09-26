FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

CONFIGFILESURI_qemuriscv64 = " \
    file://config.h \
    file://_numpyconfig.h \
"

SRC_URI_append_qemuriscv64 += "file://numpy-riscv.patch"
