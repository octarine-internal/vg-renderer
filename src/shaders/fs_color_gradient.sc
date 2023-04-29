$input v_texcoord0

#include "../../../bgfx/examples/common/common.sh"

uniform vec4 u_colors[16];
uniform vec4 u_stops[4];
uniform vec4 u_color;
uniform vec4 u_params;

#define u_type (u_params.x)
#define u_gray (u_params.y)

void main() {
	vec4 currentPos = vec4_splat(mix(length(v_texcoord0), v_texcoord0.x - v_texcoord0.y, u_type));
	vec4 color = mix(u_colors[0], u_colors[1], smoothstep(u_stops[0][0], u_stops[0][1], currentPos));
	for (int i = 1; i < 16 - 1; i++)
		color = mix(color, u_colors[i + 1], smoothstep(u_stops[i / 4][i % 4], u_stops[(i + 1) / 4][(i + 1) % 4], currentPos));
	color = toGammaAccurate(toLinearAccurate(color) * u_color);
	color = mix(color, luma(color), u_gray);
	gl_FragColor = color;
}
