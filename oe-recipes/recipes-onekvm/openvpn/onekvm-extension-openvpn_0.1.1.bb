HOMEPAGE = "https://github.com/onekvm/onekvm-extension-openvpn"
SUMMARY = "Optional OpenVPN client and TUN kernel module"
DESCRIPTION = "Installs openvpn and onekvm-kernel-module-tun. OpenVPN interfaces stay in OneKVM network settings; this package does not run a resident service."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94709c9b19d549c49355fc4fafb70d1a"
PR = "r1"

ONEKVM_COMPONENT_GIT_URI ??= "git://github.com/onekvm/onekvm-extension-openvpn.git;protocol=https;branch=main"
ONEKVM_COMPONENT_SRCREV ??= ""

SRC_URI = "${ONEKVM_COMPONENT_GIT_URI}"
SRCREV = "${ONEKVM_COMPONENT_SRCREV}"

COMPATIBLE_MACHINE = "^onekvm-"

inherit onekvm-extension

ONEKVM_EXTENSION_ID = "openvpn"
ONEKVM_EXTENSION_API_VERSION = "2"
ONEKVM_EXTENSION_MANIFEST_TEMPLATE = "${S}/manifest.json.in"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${ONEKVM_EXTENSION_ROOT} VERSION=${PV} ARCH=${TARGET_ARCH}
}

# The health check script has no debug symbols.
ALLOW_EMPTY:${PN}-dbg = "1"

# Recommends, not Depends: the installed extension host accepts only its
# platform dependency list on a single IPK. opkg still installs these with
# the plugin.
RRECOMMENDS:${PN} += " \
    openvpn \
    onekvm-kernel-module-tun \
"
