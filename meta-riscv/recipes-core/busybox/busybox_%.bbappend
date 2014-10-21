FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

ALLOW_EMPTY_${PN}-httpd = "1"
ALLOW_EMPTY_${PN}-syslog = "1"
ALLOW_EMPTY_${PN}-mdev = "1"
ALLOW_EMPTY_${PN}-udhcpd = "1"
ALLOW_EMPTY_${PN}-udhcpc = "1"
ALLOW_EMPTY_${PN}-hwclock = "1"
