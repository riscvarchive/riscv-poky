KORG_ARCHIVE_COMPRESSION = "xz"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

S = "${WORKDIR}/linux-3.14.41"

SRC_URI = "git://github.com/riscv/riscv-linux.git;branch=new_privileged_isa;destsuffix=${S} \
           https://www.kernel.org/pub/linux/kernel/v3.x/linux-3.14.41.tar.xz;name=kernel"

SRC_URI[kernel.md5sum] = "b28dfc6907c388c2adcc65aee2ad68ff"
SRC_URI[kernel.sha256sum] = "22a2291279ec075c37a66f372333a235328a1ae0a313d205d47f5b448cd3f061"

SRCREV_pn-linux-libc-headers = "4da7416c2a0d07221678c1f0afff7be87af2e728"
SRCREV_pn-nativesdk-linux-libc-headers = "4da7416c2a0d07221678c1f0afff7be87af2e728"

#do_configure_prepend () {
#  mv ${S}/arch/riscv ${S}/arch/riscv64
#}
