PV = "7.1.0"
BINV = "7.1.0"

EXTRA_OECONF += "--disable-libmudflap \
                --disable-libssp \
                --disable-libquadmath \
		--disable-libitm \
                --disable-nls \
                --enable-tls \
                --disable-multilib"
