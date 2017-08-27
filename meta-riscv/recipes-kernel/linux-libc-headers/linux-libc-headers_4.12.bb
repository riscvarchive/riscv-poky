KORG_ARCHIVE_COMPRESSION = "xz"

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

S = "${WORKDIR}/linux-4.12"

SRC_URI = "git://github.com/riscv/riscv-linux.git;destsuffix=${S};branch=riscv-next \
           file://riscv-headers.patch \
           file://linux-kbuild.patch"

SRC_URI[kernel.md5sum] = "70c4571bfb7ce7ccb14ff43b50165d43"
SRC_URI[kernel.sha256sum] = "e158f3c69da87c2ec28d0f194dbe18b05e0d0b9e1142566615cea3390bab1c6a"

SRCREV_pn-linux-libc-headers = "1cd2e072bbd1a518e11b551435be0ad84a2c52af"
SRCREV_pn-nativesdk-linux-libc-headers = "1cd2e072bbd1a518e11b551435be0ad84a2c52af"

#do_configure_prepend () {
#  mv ${S}/arch/riscv ${S}/arch/riscv64
#}

do_install() {
	bash -c "make headers_install ARCH=${ARCH} INSTALL_HDR_PATH=${D}${exec_prefix}"
	# Kernel should not be exporting this header
	rm -f ${D}${exec_prefix}/include/scsi/scsi.h

	# The ..install.cmd conflicts between various configure runs
	find ${D}${includedir} -name ..install.cmd | xargs rm -f
}
