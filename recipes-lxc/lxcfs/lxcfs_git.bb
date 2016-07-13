# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)
#
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

HOMEPAGE = "https://github.com/lxc/lxcfs/"
# No information for SRC_URI yet (only an external source tree was specified)
SRCREV ?= "${AUTOREV}"
SRC_URI = "git://github.com/lxc/lxcfs.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

# NOTE: the following prog dependencies are unknown, ignoring: help2man
DEPENDS = "fuse"
# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit pkgconfig autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:

EXTRA_OECONF = "--with-init-script=sysvinit \
                --prefix=/usr \
                --sysconfdir=/etc \
                --with-pamdir=none \
                --localstatedir=/var \
                --libexecdir=/usr/lib \
                "
do_configure() {
    build_p=$(pwd)
    cd ${S}; ./bootstrap.sh; cd $build_p
    oe_runconf
}
