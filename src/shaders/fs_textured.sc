$input v_color0, v_texcoord0

#include "../../../bgfx/examples/common/common.sh"

uniform vec4 u_color;
uniform vec4 u_params;

#define u_texelSize      (u_params.xy)
#define u_gray           (u_params.z)

SAMPLER2D(s_tex, 0);

void main() {
	vec4 texel;
#if DX9
	texel = texture2D(s_tex, v_texcoord0.xy - u_texelSize / 2);
#else
	texel = texture2D(s_tex, v_texcoord0.xy);
#endif
	vec4 color = toGammaAccurate(texel * toLinearAccurate(v_color0) * u_color);
	color = mix(color, luma(color), u_gray);
	gl_FragColor = color;
}
