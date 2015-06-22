KORG_ARCHIVE_COMPRESSION = "xz"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

S = "${WORKDIR}/linux-3.14.15"

SRC_URI = "git://github.com/riscv/riscv-linux.git;branch=new_privileged_isa;destsuffix=${S} \
            https://www.kernel.org/pub/linux/kernel/v3.x/linux-3.14.15.tar.xz;name=kernel"

SRC_URI[kernel.md5sum] = "89c2aaa23f95ca13447c35e514038472"
SRC_URI[kernel.sha256sum] = "209d4607320f83485a057f6fc366489ada2da7ea7ab409a4bc1f25a38fd15c72"

SRCREV_pn-linux-libc-headers = "4da7416c2a0d07221678c1f0afff7be87af2e728"

#do_configure_prepend () {
#  mv ${S}/arch/riscv ${S}/arch/riscv64
#}
