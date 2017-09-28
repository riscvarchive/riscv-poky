SUMMARY  = "pandas library for high-performance data analysis tools"
DESCRIPTION = "pandas is an open source, BSD-licensed library providing \
high-performance, easy-to-use data structures and data analysis tools for \
the Python programming language."
HOMEPAGE = "http://pandas.pydata.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66017d3134349017409386be85176145"

PYPI_PACKAGE = "pandas"

inherit pypi
inherit setuptools

SRC_URI[md5sum] = "42b9dba111a8f916352f49b512c029aa"
SRC_URI[sha256sum] = "d2e483692c7915916dffd1b83256ea9761b4224c8d45646ceddf48b977ee77b2"

DEPENDS += " \
  ${PYTHON_PN}-numpy-native \
  "

RDEPENDS_${PN} += " \
  ${PYTHON_PN}-numpy \
  ${PYTHON_PN}-dateutil \
  ${PYTHON_PN}-pytz \
  "
