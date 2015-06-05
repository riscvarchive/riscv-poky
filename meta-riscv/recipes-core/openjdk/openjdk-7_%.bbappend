# Variable to help copy our RISCV patch later
RISCV64_PATCH := "${THISDIR}/files/icedtea-hotspot-riscv.patch"

# Add our patch to the global path
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Add our patch into the OpenJDK 7 list of patches
# The meta-java layer deals with patches using a different pattern,
# so we're just conforming to that.
OEPATCHES += "file://build-riscv.patch"
ICEDTEA_PATCHES += "file://icedtea-hotspot-riscv.patch;apply=no"
DISTRIBUTION_PATCHES += "patches/icedtea-hotspot-riscv.patch"

# We want no additional VMs at the moment, since only Zero works.
WITH_ADDITIONAL_VMS = ""

# We overload the do_configure_prepend task in the meta-java layer so we can
# add in our patch manually
do_configure_prepend() {
  echo "Configure with parallel-jobs: ${JDK_JOBS}"

  # Automatically copy everything that starts with "icedtea" (or "cacao") and ends with
  # ".patch" into the patches directory.
  find ${WORKDIR} -maxdepth 1 -name "icedtea*.patch" -exec cp {} ${S}/patches \;
  find ${WORKDIR} -maxdepth 1 -name "cacao*.patch" -exec cp {} ${S}/patches \;
  cp ${RISCV64_PATCH} ${S}/patches
}

