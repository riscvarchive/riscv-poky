PV = "7.1.0"
BINV = "7.1.0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://riscv-strict-align.patch"

EXTRA_OECONF += "--disable-libmudflap \
                --disable-libssp \
                --disable-libquadmath \
                --disable-libitm \
                --disable-nls \
                --enable-tls \
                --disable-multilib"
