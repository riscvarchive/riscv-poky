SUMMARY = "A small image just capable of allowing a device to boot."

DEPENDS += "riscv-pk riscv-spike-native"

#IMAGE_FEATURES += "package-management"

inherit core-image

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_BOOTSTRAP_INSTALL} ${CORE_IMAGE_EXTRA_INSTALL}"

# Basic packages
IMAGE_INSTALL += "apt libffi libffi-dev sudo coreutils"

# Python
IMAGE_INSTALL += "python-numpy python-subprocess python-ctypes python-html python-netserver python-compile"

# Basic toolchain on target
IMAGE_INSTALL += "gcc binutils glibc glibc-dev libgcc libgcc-dev libstdc++ libstdc++-dev"

# Networking
#IMAGE_INSTALL += "openssh"

# Advanced packages for Ray
IMAGE_INSTALL += "cmake boost python-pip python-setuptools python-cython python-six python-pytest python-pandas"
IMAGE_INSTALL += "jemalloc apache-arrow python-pyarrow"
IMAGE_INSTALL += "redis python-redis python-cloudpickle python-click python-funcsigs python-psutil python-colorama"
IMAGE_INSTALL += "python-ray python-flatbuffers"
IMAGE_INSTALL += "openblas"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

IMAGE_ROOTFS_SIZE ?= "719820"
