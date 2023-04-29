$input a_position
$output v_texcoord0

#include <bgfx_shader.sh>

uniform mat3 u_paintMat;

void main()
{
	gl_Position = mul(u_modelViewProj, vec4(a_position, 0.0, 1.0) );
	v_texcoord0 = mul(u_paintMat, vec3(a_position, 1.0)).xy;
}
