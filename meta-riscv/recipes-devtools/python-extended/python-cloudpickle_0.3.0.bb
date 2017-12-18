SUMMARY  = "Extended pickling support for Python objects"
DESCRIPTION = "`cloudpickle` makes it possible to serialize Python constructs not supported \
by the default `pickle` module from the Python standard library."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b4d59aa5e2cc777722aac17841237931"

PYPI_PACKAGE = "cloudpickle"

inherit pypi
inherit setuptools

#cloudpickle 0.3
SRC_URI[md5sum] = "6ecb42f28daae930e47cc27a97047e7c"
SRC_URI[sha256sum] = "b85e68c749052c3fd67697dac677cabc5668f53ac5ce2a7e9e2bc2f42405bcb6"

#cloudpickle 0.4
#SRC_URI[md5sum] = "b66e7c0839e81de9b5ac84b12e3b0033"
#SRC_URI[sha256sum] = "5bb83eb466f0733dbd077e76cf1a15c404a94eb063cecc7049a1482fa1b11661"

DEPENDS += ""

RDEPENDS_${PN} += ""
