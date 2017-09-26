DESCRIPTION = "RISC-V Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "xz-native bc-native"
DEPENDS_append = " libgcc"

KERNEL_CC_append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD_append = " ${TOOLCHAIN_OPTIONS}"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.1"

COMPATIBLE_MACHINE = "riscv64"

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

SRC_URI = "git://github.com/riscv/riscv-linux.git;branch=firesim;destsuffix=${S} \
           file://mstrict-align.patch"

SRC_URI += "file://defconfig"

SRC_URI[kernel.md5sum] = "70c4571bfb7ce7ccb14ff43b50165d43"
SRC_URI[kernel.sha256sum] = "e158f3c69da87c2ec28d0f194dbe18b05e0d0b9e1142566615cea3390bab1c6a"

# uncomment and replace these SRCREVs with the real commit ids once you've had
# the appropriate changes committed to the upstream linux-yocto repo
SRCREV_pn-linux-riscv ?= "7633e79728ebcc2ba5fc6e291b7bbf1f7374f282"
LINUX_VERSION = "4.12"

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
