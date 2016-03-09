FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://gcc-5.3-riscv.patch file://gcc-5.3-riscv-musl.patch"

EXTRA_OECONF += "--disable-libmudflap \
                --disable-libssp \
                --disable-libquadmath \
                --disable-nls \
                --enable-tls \
                --disable-multilib"
