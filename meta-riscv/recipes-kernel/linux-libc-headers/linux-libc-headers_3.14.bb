KORG_ARCHIVE_COMPRESSION = "xz"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

S = "${WORKDIR}/linux-3.14.41"

SRC_URI = "git://github.com/riscv/riscv-linux.git;branch=master;destsuffix=${S} \
           https://www.kernel.org/pub/linux/kernel/v3.x/linux-3.14.41.tar.xz;name=kernel"

SRC_URI[kernel.md5sum] = "b28dfc6907c388c2adcc65aee2ad68ff"
SRC_URI[kernel.sha256sum] = "22a2291279ec075c37a66f372333a235328a1ae0a313d205d47f5b448cd3f061"

SRCREV_pn-linux-libc-headers = "886e5f99498baa2c4d1c1e3e1db801e6f4ae68c1"
SRCREV_pn-nativesdk-linux-libc-headers = "886e5f99498baa2c4d1c1e3e1db801e6f4ae68c1"

#do_configure_prepend () {
#  mv ${S}/arch/riscv ${S}/arch/riscv64
#}
