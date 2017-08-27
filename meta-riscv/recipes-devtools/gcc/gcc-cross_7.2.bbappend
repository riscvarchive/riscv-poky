PV = "7.2.0"
BINV = "7.2.0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://riscv-legitimize-move.patch"

EXTRA_OECONF += "--disable-libmudflap \
                --disable-libssp \
                --disable-libquadmath \
		--disable-libitm \
                --disable-nls \
                --enable-tls \
                --disable-multilib"
