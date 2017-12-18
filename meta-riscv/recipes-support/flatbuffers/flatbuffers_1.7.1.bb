SUMMARY = "an efficient cross platform serialization library for games and other memory constrained apps"
DESCRIPTION = "an efficient cross platform serialization library for games and other memory constrained apps.\
 It allows you to directly access serialized data without unpacking/parsing it first, while still having great forwards/backwards compatibility."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a873c5645c184d51e0f9b34e1d7cf559"


SRC_URI = "\
       https://github.com/google/flatbuffers/archive/v1.7.1.tar.gz \
       file://0001-correct-version-for-so-lib.patch \
"
SRC_URI[md5sum] = "81934736f31fbd2cfdb513e71b53b358"
SRC_URI[sha256sum] = "0f8dcdcfbbaba034756f97e4f9946a3784399b578f1b60c8deee3c85fafa3089"

S = "${WORKDIR}/flatbuffers-1.7.1/" 

inherit cmake texinfo

CXXFLAGS += "-std=c++11"
BUILD_CXXFLAGS += "-std=c++11"

# Make sure C++11 is used, required for example for GCC 4.9
EXTRA_OECMAKE += "-DCMAKE_CXX_FLAGS+='-fPIC -std=c++11' -DCMAKE_BUILD_CXXFLAGS+='-std=c++11' -DFLATBUFFERS_BUILD_TESTS=OFF -DFLATBUFFERS_BUILD_SHAREDLIB=ON"
#EXTRA_OECMAKE += "-DCMAKE_CXX_FLAGS+='-fPIC -std=c++11' -DCMAKE_BUILD_CXXFLAGS+='-std=c++11' -DFLATBUFFERS_BUILD_TESTS=ON -DFLATBUFFERS_BUILD_SHAREDLIB=ON"

BBCLASSEXTEND = "native"
