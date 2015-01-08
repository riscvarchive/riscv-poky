DESCRIPTION = "RISC-V Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "xz-native bc-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.1"

COMPATIBLE_MACHINE = "(qemuriscv|riscv)"

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

S = "${WORKDIR}/linux-3.14.15"

SRC_URI += "git://github.com/martinmaas/riscv-linux.git;branch=qemu-coredump;destsuffix=${S} \
            https://www.kernel.org/pub/linux/kernel/v3.x/linux-3.14.15.tar.xz;name=kernel"

SRC_URI += "file://defconfig"

SRC_URI[kernel.md5sum] = "89c2aaa23f95ca13447c35e514038472"
SRC_URI[kernel.sha256sum] = "209d4607320f83485a057f6fc366489ada2da7ea7ab409a4bc1f25a38fd15c72"

# uncomment and replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto repo
#SRCREV_machine_pn-linux-yocto_riscv ?= "840bb8c059418c4753415df56c9aff1c0d5354c8"
SRCREV_pn-linux-riscv ?= "1b8a11d36516559dc4dfd7b3b2c3ea2aee047ea9"
LINUX_VERSION = "3.14"

do_patch[depends] = "kern-tools-native:do_populate_sysroot"

# Pick up shared functions
inherit kernel

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
