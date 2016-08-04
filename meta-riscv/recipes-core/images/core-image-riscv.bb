SUMMARY = "A small image just capable of allowing a device to boot."

DEPENDS += "riscv-pk"

IMAGE_FEATURES += "package-management "

inherit core-image

IMAGE_INSTALL = "packagegroup-core-boot gcc binutils glibc glibc-dev libgcc libgcc-dev make ncurses bash bison flex ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

IMAGE_ROOTFS_SIZE = "524288"

