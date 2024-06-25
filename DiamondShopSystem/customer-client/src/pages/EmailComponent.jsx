import React, { useEffect, useState } from "react";
import AuthService from "../components/AuthService";

function EmailComponent() {
    const [itemDetails, setItemDetails] = useState([]);

    useEffect(() => {
        fetchCarts();
    }, []);

    const fetchCarts = async () => {
        try {
            const token = localStorage.getItem('token');
            const data = await AuthService.getCart(token);
            setItemDetails(data.listOrderDetail.map(item => ({
                ...item.product.jewelry,
                orderDetailId: item.id,
                quantity: item.quantity,
            })));
        } catch (error) {
            console.error("Error fetching users data:", error);
        }
    };

    const totalAmount = itemDetails.reduce((acc, item) => acc + item.price * item.quantity, 0);

    return (
        <div style={{
            backgroundColor: '#f2f3f8',
            padding: '20px',
            fontFamily: 'Arial, sans-serif',
            color: '#455056',
            maxWidth: '670px',
            margin: '0 auto',
        }}>
            <table width="100%" border="0" cellPadding="0" cellSpacing="0" style={{ backgroundColor: '#f2f3f8', margin: '0 auto' }}>
                <tbody>
                    <tr>
                        <td>
                            <table width="100%" border="0" align="center" cellPadding="0" cellSpacing="0" style={{
                                background: '#fff',
                                borderRadius: '3px',
                                textAlign: 'center',
                                boxShadow: '0 6px 18px 0 rgba(0,0,0,.06)',
                                margin: '15px auto',
                                maxWidth: '670px',
                                padding: '40px 35px',
                            }}>
                                <tbody>
                                    <tr style={{ margin: '10px 0px' }}>
                                        <td style={{ padding: '10px 35px' }}>
                                            <h1 style={{
                                                color: '#1e1e2d',
                                                fontWeight: '500',
                                                margin: '0',
                                                fontSize: '32px'
                                            }}>
                                                Thank You!
                                            </h1>
                                            <p style={{
                                                fontSize: '15px',
                                                margin: '8px 0 0',
                                                lineHeight: '24px',
                                            }}>
                                                <strong>We deeply appreciate that you have chosen us.</strong>
                                            </p>
                                            <span style={{
                                                display: 'inline-block',
                                                verticalAlign: 'middle',
                                                margin: '29px 0 26px',
                                                borderBottom: '1px solid #cecece',
                                                width: '100px'
                                            }}></span>
                                            <p style={{
                                                fontSize: '18px',
                                                lineHeight: '20px',
                                                margin: '0',
                                                fontWeight: '500'
                                            }}>
                                                <strong style={{
                                                    display: 'block',
                                                    fontSize: '25px',
                                                    margin: '0 0 4px',
                                                    color: '#000',
                                                    fontWeight: 'bold'
                                                }}>H E P H A E S T U S</strong>
                                                <strong style={{
                                                    display: 'block',
                                                    fontSize: '15px',
                                                    margin: '24px 0 4px 0',
                                                    fontWeight: 'bold',
                                                    color: 'rgba(0,0,0,.64)'
                                                }}>
                                                    Hope to see you again in the future.
                                                </strong>
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style={{ padding: '0 35px' }}>
                                            <h2 style={{
                                                color: '#1e1e2d',
                                                fontWeight: '500',
                                                margin: '0',
                                                fontSize: '24px',
                                                marginTop: '40px'
                                            }}>
                                                Your Order
                                            </h2>
                                            <span style={{
                                                display: 'inline-block',
                                                verticalAlign: 'middle',
                                                margin: '20px 0',
                                                borderBottom: '1px solid #cecece',
                                                width: '100px'
                                            }}></span>
                                            {itemDetails.map((item, index) => (
                                                <div key={index} style={{ display: 'flex', alignItems: 'center', marginBottom: '1.5vh' }}>
                                                    <div style={{ flex: '1', paddingRight: '1rem' }}>
                                                        <img src={item.img} alt={item.name} style={{ borderRadius: '7px', width: '80%', height: '80%' }} />
                                                    </div>
                                                    <div style={{ flex: '2', textAlign: 'left' }}>
                                                        <div style={{ marginBottom: '0.5rem' }}>
                                                            <p style={{ fontSize: '0.9rem', fontWeight: 'bold', margin: '0' }}>{item.name}</p>
                                                            <p style={{ fontSize: '0.9rem', color: 'grey', margin: '0' }}>Quantity: {item.quantity}</p>
                                                        </div>
                                                        <div style={{ textAlign: 'right' }}>
                                                            <p style={{ fontSize: '0.9rem', fontWeight: 'bold', margin: '0' }}>${item.price.toFixed(2)}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            ))}
                                            <hr style={{ margin: '1rem -1vh', borderTop: '1px solid rgb(214, 214, 214)' }} />
                                            <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: '1rem', fontWeight: 'bold', marginBottom: '2rem' }}>
                                                <span>Total:</span>
                                                <span>${totalAmount.toFixed(2)}</span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style={{ padding: '0 20px', textAlign: 'center', marginTop: '40px' }}>
                                            <div style={{ display: 'flex', justifyContent: 'center', gap: '1rem' }}>
                                                <div style={{ textAlign: 'center' }}>
                                                    <p style={{ margin: '0', fontWeight: 'bold', color: 'rgba(0,0,0,.64)', fontSize: '14px' }}>Something is wrong?</p>
                                                    <a href="tel:+1234567890" style={{
                                                        background: '#000',
                                                        textDecoration: 'none',
                                                        display: 'inline-block',
                                                        fontWeight: '500',
                                                        marginTop: '8px',
                                                        color: '#fff',
                                                        textTransform: 'uppercase',
                                                        fontSize: '14px',
                                                        padding: '15px 24px',
                                                        borderRadius: '50px',
                                                        transition: 'background-color 0.3s ease',
                                                        textAlign: 'center',
                                                        width: 'fit-content',
                                                    }}>
                                                        Contact Our Customer Support
                                                    </a>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style={{ height: '40px' }}>&nbsp;</td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style={{ textAlign: 'center' }}>
                            <p style={{ fontSize: '14px', color: 'rgba(69, 80, 86, 0.74)', lineHeight: '18px', margin: '0' }}>
                                &copy; <strong>www.hephaestus.com</strong>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td style={{ height: '5px' }}>&nbsp;</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default EmailComponent;
