SUMMARY  = "The FlatBuffers serialization format for Python"
DESCRIPTION = "Python runtime library for use with the Flatbuffers serialization format"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=524b31a94ac79c7623a25f8e61644aec"

PYPI_PACKAGE = "flatbuffers"

inherit pypi
inherit setuptools

SRC_URI[md5sum] = "94a5031f568a5082b5c5ba68d1b66e94"
SRC_URI[sha256sum] = "8b2d306ba5f26aadd3fb64183174f0d5e7e8cf19b9e66ed4e814a6fb12c24d99"

DEPENDS += " \
  ${PYTHON_PN}-numpy-native \
  "
