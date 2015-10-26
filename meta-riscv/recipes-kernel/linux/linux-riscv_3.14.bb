DESCRIPTION = "RISC-V Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "xz-native bc-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.1"

COMPATIBLE_MACHINE = "(qemuriscv64|riscv64)"

LINUX_KERNEL_TYPE ?= "standard"

# KMETA ?= ""
KBRANCH ?= "master"
KMACHINE ?= "${MACHINE}"
KMETA = "meta"

#KERNEL_FEATURES_append_riscv += " cfg/smp.scc"

KERNEL_ALT_IMAGETYPE = ""

KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"

#SRC_URI += "file://riscv-standard.scc \
#            file://riscv-user-config.cfg \
#            file://riscv-user-patches.scc \
#            file://riscv-user-features.scc \
#           "

#S = "${WORKDIR}/linux-3.14.41"

# Pick up shared functions
inherit kernel

SRC_URI += "git://github.com/riscv/riscv-linux.git;branch=master;destsuffix=${S} \
            https://www.kernel.org/pub/linux/kernel/v3.x/linux-3.14.41.tar.xz;name=kernel"

do_overlay_kernel() {
  cp -R ${WORKDIR}/linux-3.14.41/* ${S}
}

#ln -s ${S}/arch/riscv ${S}/arch/riscv64

do_unpack_append () {
    bb.build.exec_func('do_overlay_kernel', d)
}

SRC_URI += "file://defconfig"

SRC_URI[kernel.md5sum] = "b28dfc6907c388c2adcc65aee2ad68ff"
SRC_URI[kernel.sha256sum] = "22a2291279ec075c37a66f372333a235328a1ae0a313d205d47f5b448cd3f061"

# uncomment and replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto repo
#SRCREV_machine_pn-linux-yocto_riscv ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_pn-linux-riscv ?= "886e5f99498baa2c4d1c1e3e1db801e6f4ae68c1"
LINUX_VERSION = "3.14"

do_patch[depends] = "kern-tools-native:do_populate_sysroot"

do_configure_prepend() {
  # If we have supplied a configuration and there is already one, delete it.
  if [ -f "${WORKDIR}/defconfig" ] && [ -f "${B}/.config" ]; then
    rm "${B}/.config"
  fi
}

do_install_prepend() {
  # We are not building any modules, but the directory needs to be there.
  mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/build
}
