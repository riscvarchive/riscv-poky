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
KERNEL_OUTPUT_DIR = "."

PROVIDES = "riscv-linux"

# Pick up shared functions
inherit kernel

SRC_URI += "git://github.com/riscv/riscv-linux.git;branch=riscv-next;destsuffix=${S} \
            https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.6.2.tar.xz;name=kernel \
            file://riscvemu.patch"

do_overlay_kernel() {
  cp -R ${WORKDIR}/linux-4.6.2/* ${S}
}


do_unpack_append () {
    bb.build.exec_func('do_overlay_kernel', d)
}

SRC_URI += "file://defconfig"

SRC_URI[kernel.md5sum] = "70c4571bfb7ce7ccb14ff43b50165d43"
SRC_URI[kernel.sha256sum] = "e158f3c69da87c2ec28d0f194dbe18b05e0d0b9e1142566615cea3390bab1c6a"

# uncomment and replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto repo
#SRCREV_machine_pn-linux-yocto_riscv ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_pn-linux-riscv ?= "849317594f03930a09e5139c6196c9930e06035b"
LINUX_VERSION = "4.6"

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
