PV = "6.1.0"
BINV = "6.1.0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://gcc-6.1-riscv.patch"

EXTRA_OECONF += "--disable-libmudflap \
                --disable-libssp \
                --disable-libquadmath \
                --disable-libitm \
                --disable-nls \
                --enable-tls \
                --disable-multilib"
