KORG_ARCHIVE_COMPRESSION = "xz"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

S = "${WORKDIR}/linux-4.6.2"

SRC_URI = "git://github.com/riscv/riscv-linux.git;branch=riscv-next;destsuffix=${S} \
           https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.6.2.tar.xz;name=kernel \
           file://riscvemu.patch"

SRC_URI[kernel.md5sum] = "70c4571bfb7ce7ccb14ff43b50165d43"
SRC_URI[kernel.sha256sum] = "e158f3c69da87c2ec28d0f194dbe18b05e0d0b9e1142566615cea3390bab1c6a"

SRCREV_pn-linux-libc-headers = "849317594f03930a09e5139c6196c9930e06035b"
SRCREV_pn-nativesdk-linux-libc-headers = "849317594f03930a09e5139c6196c9930e06035b"

#do_configure_prepend () {
#  mv ${S}/arch/riscv ${S}/arch/riscv64
#}
