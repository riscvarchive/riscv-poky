KORG_ARCHIVE_COMPRESSION = "xz"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

S = "${WORKDIR}/linux-4.1.17"

SRC_URI = "git://github.com/riscv/riscv-linux.git;branch=master;destsuffix=${S} \
           https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.1.17.tar.xz;name=kernel"

SRC_URI[kernel.md5sum] = "4b0bd6b3775170ce0f76ef219ac279d8"
SRC_URI[kernel.sha256sum] = "4b19988e252d4a954d86f9a5aa2d32e11e6131ad182f25b3a447d4b753909120"

SRCREV_pn-linux-libc-headers = "174f39501397a32c3d9c3220e3b55ec20c16303f"
SRCREV_pn-nativesdk-linux-libc-headers = "174f39501397a32c3d9c3220e3b55ec20c16303f"

#do_configure_prepend () {
#  mv ${S}/arch/riscv ${S}/arch/riscv64
#}
